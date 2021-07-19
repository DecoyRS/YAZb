package com.github.decoyrs.ziggij.projectModel

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.ide.util.projectWizard.ModuleWizardStep
import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.openapi.Disposable
import com.intellij.openapi.projectRoots.SdkTypeId
import com.intellij.openapi.roots.ui.configuration.ModulesProvider
import com.intellij.openapi.util.Disposer

class ZigLibraryModuleBuilder : ModuleBuilder() {
    override fun getModuleType() = ZigLibraryModuleType.INSTANCE
    override fun isSuitableSdkType(sdkType: SdkTypeId?) = true
    override fun getCustomOptionsStep(context: WizardContext, parentDisposable: Disposable) =
        ZigLibraryProjectWizardStep().apply {
            Disposer.register(parentDisposable, this::disposeUIResources)
        }

    override fun createWizardSteps(
        wizardContext: WizardContext,
        modulesProvider: ModulesProvider
    ): Array<ModuleWizardStep> {
        return arrayOf(ZigLibraryProjectWizardStep())
    }
}
