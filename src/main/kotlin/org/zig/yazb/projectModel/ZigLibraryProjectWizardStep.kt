@file:Suppress("HardCodedStringLiteral")

package org.zig.yazb.projectModel

import org.zig.yazb.YAZbBundle
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.ui.layout.PropertyBinding
import com.intellij.ui.layout.panel
import java.io.File
import javax.swing.DefaultComboBoxModel

class ZigLibraryProjectWizardStep : ModuleWizardStep() {
    private val libraryTypes = arrayOf("static", "shared")
    private var defaultLibraryType: String = libraryTypes.first()

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
        row {
            label("Type")
            comboBox<String>(DefaultComboBoxModel(libraryTypes), PropertyBinding(
                get = { defaultLibraryType },
                set = { defaultLibraryType = it ?: libraryTypes.first() })
            )
        }
    }

    override fun updateDataModel() {
        TODO("Not yet implemented")
    }
}
