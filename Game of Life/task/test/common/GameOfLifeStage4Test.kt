package common


import org.assertj.swing.fixture.JLabelFixture
import org.hyperskill.hstest.v6.stage.SwingTest
import org.hyperskill.hstest.v6.testcase.CheckResult
import org.hyperskill.hstest.v6.testcase.TestCase
import life.GameOfLife

abstract class GameOfLifeStage4Test : SwingTest<ClueWithChecker>(GameOfLife()) {

    override fun generate(): List<TestCase<ClueWithChecker>> {
        val generationLabel = ComponentRequirements("GenerationLabel", isEnabled = true) { window.label(it) }
        val aliveLabel = ComponentRequirements("AliveLabel", isEnabled = true) { window.label(it) }

        return frameTests(::frame) +
                existenceTests(generationLabel, aliveLabel) +
                componentsAreEnabledTests(generationLabel, aliveLabel) +
                stage4Tests(
                        generationLabelRequirements = generationLabel,
                        aliveLabelRequirements = aliveLabel
                )
    }

    override fun check(reply: String, clue: ClueWithChecker): CheckResult {
        return checkClueWithCheckerTest(reply = reply, clue = clue)
    }
}

fun stage4Tests(
        generationLabelRequirements: ComponentRequirements<JLabelFixture>,
        aliveLabelRequirements: ComponentRequirements<JLabelFixture>
): List<TestCase<ClueWithChecker>> {
    return listOf(
            createDynamicFeedbackTest {
                with(checkLabelForInteger(generationLabelRequirements)) {
                    if (!this.isCorrect) {
                        return@createDynamicFeedbackTest this
                    }
                }
                with(checkLabelForInteger(aliveLabelRequirements)) {
                    if (!this.isCorrect) {
                        return@createDynamicFeedbackTest this
                    }
                }

                return@createDynamicFeedbackTest CheckResult.TRUE
            }
    )
}
