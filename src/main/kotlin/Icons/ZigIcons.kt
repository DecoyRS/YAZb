@file:Suppress("HardCodedStringLiteral", "PackageName", "PackageNaming")

package Icons

import com.intellij.openapi.util.IconLoader
import com.intellij.util.IconUtil

object ZigIcons {
    val FILE_ICON = IconLoader.getIcon("/Icons/zig_file.png", IconUtil::class.java)
    val ZIG_BIG_ICON = IconLoader.getIcon("/Icons/zig_icon.png", IconUtil::class.java)
    val ZIG_VAR = IconLoader.getIcon("/Icons/zig_variable.png", IconUtil::class.java)
    val ZIG_FUN = IconLoader.getIcon("/Icons/zig_function.png", IconUtil::class.java)
}
