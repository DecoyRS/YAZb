package com.github.decoyrs.ziggij.projectModel

import com.github.decoyrs.ziggij.ZiggIjBundle
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.ui.layout.panel
import java.io.File

class ZigExecutableProjectWizardStep(private val context: WizardContext) : ModuleWizardStep() {
    override fun getComponent() = panel {
        row {
            label(ZiggIjBundle.message("ziggij.projectModel.wizardStep.projectPath"))
            textFieldWithBrowseButton(
                ZiggIjBundle.message("ziggij.projectModel.wizardStep.projectPath.dialog.title"), fileChooserDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
            ).withValidationOnInput {
                val path = File(it.text)
                if(path.exists() && path.isDirectory.not())
                {
                    return@withValidationOnInput error(ZiggIjBundle.message("ziggij.projectModel.wizardStep.projectPath.dialog.errorNotDir", it.text))
                }
                if(path.exists() && path.listFiles().isNotEmpty())
                {
                    return@withValidationOnInput error(ZiggIjBundle.message("ziggij.projectModel.wizardStep.projectPath.dialog.errorNotEmpty", it.text))
                }
                return@withValidationOnInput null
            }.focused()
        }
        row {
            label(ZiggIjBundle.message("ziggij.projectModel.wizardStep.executablePath"))
        }
    }

    override fun updateDataModel() {
        TODO("Not yet implemented")
    }
}