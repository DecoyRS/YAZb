package com.github.decoyrs.ziggij.projectModel

import Icons.ZigIcons
import com.github.decoyrs.ziggij.ZiggIjBundle
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager

class ZigLibraryModuleType: ModuleType<ZigLibraryModuleBuilder>(ID) {
    companion object {
        const val ID ="ZIG_LIBRARY_MODULE_TYPE"
        val INSTANCE: ZigLibraryModuleType by lazy { ModuleTypeManager.getInstance().findByID(ID) as ZigLibraryModuleType}
    }

    override fun createModuleBuilder() = ZigLibraryModuleBuilder()
    override fun getName() = ZiggIjBundle.message("ziggij.projectModel.wizardStep.libraryProject.text")
    override fun getDescription() = ZiggIjBundle.message("ziggij.projectModel.wizardStep.libraryProject.description")
    override fun getNodeIcon(isOpened: Boolean) = ZigIcons.ZIG_BIG_ICON
}