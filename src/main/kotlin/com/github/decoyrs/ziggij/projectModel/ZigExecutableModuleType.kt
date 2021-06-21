package com.github.decoyrs.ziggij.projectModel

import Icons.ZigIcons
import com.github.decoyrs.ziggij.ZiggIjBundle
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager

class ZigExecutableModuleType: ModuleType<ZigExecutableModuleBuilder>(ID) {
    companion object {
        const val ID ="ZIG_EXECUTABLE_MODULE_TYPE"
        val INSTANCE: ZigExecutableModuleType by lazy {ModuleTypeManager.getInstance().findByID(ID) as ZigExecutableModuleType}
    }

    override fun createModuleBuilder() = ZigExecutableModuleBuilder()
    override fun getName() = ZiggIjBundle.message("ziggij.projectModel.wizardStep.executableProject.text")
    override fun getDescription() = ZiggIjBundle.message("ziggij.projectModel.wizardStep.executableProject.description")
    override fun getNodeIcon(isOpened: Boolean) = ZigIcons.ZIG_BIG_ICON
}