package org.zig.yazb.projectModel

import Icons.ZigIcons
import org.zig.yazb.YAZbBundle
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager

class ZigLibraryModuleType : ModuleType<ZigLibraryModuleBuilder>(ID) {
    companion object {
        @Suppress("HardCodedStringLiteral")
        const val ID = "ZIG_LIBRARY_MODULE_TYPE"
        val INSTANCE: ZigLibraryModuleType by lazy {
            ModuleTypeManager.getInstance().findByID(ID) as ZigLibraryModuleType
        }
    }

    override fun createModuleBuilder() = ZigLibraryModuleBuilder()
    override fun getName() = YAZbBundle.message("yazb.projectModel.wizardStep.libraryProject.text")
    override fun getDescription() = YAZbBundle.message("yazb.projectModel.wizardStep.libraryProject.description")
    override fun getNodeIcon(isOpened: Boolean) = ZigIcons.ZIG_BIG_ICON
}
