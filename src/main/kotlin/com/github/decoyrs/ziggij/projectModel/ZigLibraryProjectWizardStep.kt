package com.github.decoyrs.ziggij.projectModel

import com.github.decoyrs.ziggij.ZiggIjBundle
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.ui.layout.PropertyBinding
import com.intellij.ui.layout.panel
import java.io.File
import javax.swing.DefaultComboBoxModel

class ZigLibraryProjectWizardStep(private val context: WizardContext) : ModuleWizardStep() {
    private val LIBRARY_TYPES = arrayOf("statis", "shared")
    private var libraryType: String = LIBRARY_TYPES.first()

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
        row {
            label("Type")
            comboBox<String>(DefaultComboBoxModel(LIBRARY_TYPES), PropertyBinding(
                get = { libraryType },
                set = { libraryType = it?:LIBRARY_TYPES.first() })
            )
        }
    }

    override fun updateDataModel() {
        TODO("Not yet implemented")
    }
}