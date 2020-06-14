package common


import org.assertj.swing.fixture.AbstractJComponentFixture
import org.hyperskill.hstest.v6.stage.SwingTest
import org.hyperskill.hstest.v6.testcase.TestCase
import javax.swing.JFrame

fun frameTests(frameGetter: () -> JFrame): List<TestCase<ClueWithChecker>> {
    return listOf(
            createPredefinedFeedbackTest("Window is not visible") { frameGetter().isVisible }
    )
}

class ComponentRequirements<ComponentType : AbstractJComponentFixture<*, *, *>>(
        val name: String,
        val isEnabled: Boolean,
        private val componentFinder: (String) -> ComponentType?
) {
    val suitableComponent: ComponentType? by lazy { componentFinder(name) }
}

fun existenceTests(vararg components: ComponentRequirements<*>): List<TestCase<ClueWithChecker>> {
    fun generateExistenceTest(requirements: ComponentRequirements<*>): TestCase<ClueWithChecker> {
        return createPredefinedFeedbackTest("No suitable component with name '${requirements.name}' is found") {
            SwingTest.checkExistence { requirements.suitableComponent }
        }
    }

    return components.map(::generateExistenceTest)
}

fun componentsAreEnabledTests(vararg components: ComponentRequirements<*>): List<TestCase<ClueWithChecker>> {
    fun generateIsEnabledTest(requirements: ComponentRequirements<*>): TestCase<ClueWithChecker> {
        val (desiredState, stateChecker) = if (requirements.isEnabled) {
            "enabled" to { requirements.requireExistingComponent().isEnabled }
        } else {
            "disabled" to { !requirements.requireExistingComponent().isEnabled }
        }

        return createPredefinedFeedbackTest("The '${requirements.name}' component should be $desiredState") {
            stateChecker()
        }

    }

    return components.map(::generateIsEnabledTest)
}

fun <ComponentType : AbstractJComponentFixture<*, *, *>>
        ComponentRequirements<ComponentType>.requireExistingComponent(): ComponentType {
    return requireNotNull(this.suitableComponent) {
        "Must check for the '${this.name}' component existence before this test"
    }
}
