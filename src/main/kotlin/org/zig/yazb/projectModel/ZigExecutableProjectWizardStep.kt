package org.zig.yazb.projectModel

import org.zig.yazb.YAZbBundle
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.ui.layout.panel
import java.io.File

class ZigExecutableProjectWizardStep : ModuleWizardStep() {
    override fun getComponent() = panel {
        row {
            label(YAZbBundle.message("yazb.projectModel.wizardStep.projectPath"))
            textFieldWithBrowseButton(
                YAZbBundle.message("yazb.projectModel.wizardStep.projectPath.dialog.title"),
                fileChooserDescriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor()
            ).withValidationOnInput {
                val path = File(it.text)
                if (path.exists() && path.isDirectory.not()) {
                    return@withValidationOnInput error(YAZbBundle.message("yazb.projectModel.wizardStep.projectPath.dialog.errorNotDir", it.text))
                }
                if (path.exists() && path.listFiles()!!.isNotEmpty()) {
                    return@withValidationOnInput error(YAZbBundle.message("yazb.projectModel.wizardStep.projectPath.dialog.errorNotEmpty", it.text))
                }
                return@withValidationOnInput null
            }.focused()
        }
        row {
            label(YAZbBundle.message("yazb.projectModel.wizardStep.executablePath"))
        }
    }

    override fun updateDataModel() {
        TODO("Not yet implemented")
    }
}
