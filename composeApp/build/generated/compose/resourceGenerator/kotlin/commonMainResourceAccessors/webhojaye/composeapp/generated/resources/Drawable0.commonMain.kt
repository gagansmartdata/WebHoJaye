@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package webhojaye.composeapp.generated.resources

import kotlin.OptIn
import org.jetbrains.compose.resources.DrawableResource

private object CommonMainDrawable0 {
  public val compose_multiplatform: DrawableResource by 
      lazy { init_compose_multiplatform() }

  public val my_pic: DrawableResource by 
      lazy { init_my_pic() }
}

internal val Res.drawable.compose_multiplatform: DrawableResource
  get() = CommonMainDrawable0.compose_multiplatform

private fun init_compose_multiplatform(): DrawableResource =
    org.jetbrains.compose.resources.DrawableResource(
  "drawable:compose_multiplatform",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/webhojaye.composeapp.generated.resources/drawable/compose-multiplatform.xml", -1, -1),
    )
)

internal val Res.drawable.my_pic: DrawableResource
  get() = CommonMainDrawable0.my_pic

private fun init_my_pic(): DrawableResource = org.jetbrains.compose.resources.DrawableResource(
  "drawable:my_pic",
    setOf(
      org.jetbrains.compose.resources.ResourceItem(setOf(),
    "composeResources/webhojaye.composeapp.generated.resources/drawable/my_pic.jpeg", -1, -1),
    )
)
