package com.github.decoyrs.ziggij.projectModel

import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.ui.layout.panel
import javax.swing.JLabel

class ZigProjectWizardStep(private val context: WizardContext) : ModuleWizardStep() {
    override fun getComponent() = JLabel("Test ZIg Module")

    override fun updateDataModel() {
        TODO("Not yet implemented")
    }
}