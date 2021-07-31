package org.zig.yazb.projectModel

import Icons.ZigIcons
import org.zig.yazb.YAZbBundle
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager

class ZigExecutableModuleType : ModuleType<ZigExecutableModuleBuilder>(ID) {
    companion object {
        @Suppress("HardCodedStringLiteral")
        const val ID = "ZIG_EXECUTABLE_MODULE_TYPE"
        val INSTANCE: ZigExecutableModuleType by lazy {
            ModuleTypeManager.getInstance().findByID(ID) as ZigExecutableModuleType
        }
    }

    override fun createModuleBuilder() = ZigExecutableModuleBuilder()
    override fun getName() = YAZbBundle.message("yazb.projectModel.wizardStep.executableProject.text")
    override fun getDescription() = YAZbBundle.message("yazb.projectModel.wizardStep.executableProject.description")
    override fun getNodeIcon(isOpened: Boolean) = ZigIcons.ZIG_BIG_ICON
}
