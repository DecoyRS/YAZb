package com.github.decoyrs.ziggij.projectModel

import Icons.ZigIcons
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager

class ZigModuleType: ModuleType<ZigModuleBuilder>(ID) {
    companion object {
        const val ID ="ZIG_MODULE_TYPE"
        val INSTANCE: ZigModuleType by lazy {ModuleTypeManager.getInstance().findByID(ID) as ZigModuleType}
    }

    override fun createModuleBuilder() = ZigModuleBuilder()

    override fun getName() = "Zig Console App"

    override fun getDescription() = "Zig Console App"

    override fun getNodeIcon(isOpened: Boolean) = ZigIcons.ZIG_BIG_ICON
}