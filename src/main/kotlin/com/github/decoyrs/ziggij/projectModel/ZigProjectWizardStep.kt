package com.github.decoyrs.ziggij.projectModel

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import javax.swing.JLabel

class ZigProjectWizardStep : ModuleWizardStep() {
    @Suppress("HardCodedStringLiteral")
    override fun getComponent() = JLabel("Test Zig Module")

    override fun updateDataModel() {
        TODO("Not yet implemented")
    }
}
