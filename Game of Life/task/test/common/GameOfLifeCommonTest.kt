package common


import org.assertj.swing.fixture.JLabelFixture
import org.hyperskill.hstest.v6.testcase.CheckResult

fun checkLabelForInteger(labelRequirements: ComponentRequirements<JLabelFixture>): CheckResult {
    val label = labelRequirements.requireExistingComponent()

    val labelDigits = label.text().trim { !it.isDigit() }

    if (labelDigits.toIntOrNull() == null) {
        return fail("The '${labelRequirements.name}' label doesn't contain an integer.")
    }

    return CheckResult.TRUE
}
