package other

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.item.res.itemXmlTemplate
import other.item.src.itemTemplate


// String activityClass, @NotNull String layoutName, @NotNull String packageName, boolean isCreateDataBinding
fun RecipeExecutor.itemRecipe(
    moduleData: ModuleTemplateData,
    activityClass: String,
    layoutName: String,
    packageName: String,
    isCreateDataBinding: Boolean
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
    save(
        itemTemplate(projectData.applicationPackage?:"", activityClass, layoutName, packageName, isCreateDataBinding),
        srcOut.resolve("item/${activityClass}Item.${ktOrJavaExt}")
    )
    save(
        itemXmlTemplate(packageName, activityClass, isCreateDataBinding),
        resOut.resolve("layout/${layoutName}.xml")
    )
}