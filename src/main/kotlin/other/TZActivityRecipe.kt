package other


import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import other.activity.res.mvvmActivityXml
import other.activity.src.mvvmActivityKt
import other.activity.src.mvvmFragmentKt
import other.activity.src.mvvmRepository
import other.activity.src.mvvmViewModel

fun RecipeExecutor.mvvmActivityRecipe(
    moduleData: ModuleTemplateData,
    activityClass: String,
    layoutName: String,
    packageName: String,
    viewType:EType,
    isCreateView: Boolean,
    isCreateViewModel: Boolean,
    isCreateRepository: Boolean,
    isUseDataBinding:Boolean,
) {
    val (projectData, srcOut, resOut) = moduleData
    val ktOrJavaExt = projectData.language.extension
    if(viewType==EType.Activity){
        if(isCreateView){
            val mvvmActivity = mvvmActivityKt(projectData.applicationPackage, activityClass, layoutName, packageName,isCreateViewModel,isUseDataBinding)
            // 保存Activity
            save(mvvmActivity, srcOut.resolve("view/${activityClass}Activity.${ktOrJavaExt}"))
            // 保存xml
            save(mvvmActivityXml(packageName, activityClass,isCreateViewModel,isUseDataBinding), resOut.resolve("layout/${layoutName}.xml"))
            // 创建Manifast
            generateManifest(
                moduleData = moduleData,
                activityClass = "view.${activityClass}Activity",
                packageName = packageName,
                isLauncher = false,
                hasNoActionBar = false,
                generateActivityTitle = false,
            )
        }
    }else if(viewType==EType.Fragment){
        if(isCreateView){
            val mvvmActivity = mvvmFragmentKt(projectData.applicationPackage, activityClass, layoutName, packageName,isCreateViewModel,isUseDataBinding)
            // 保存Activity
            save(mvvmActivity, srcOut.resolve("view/${activityClass}Fragment.${ktOrJavaExt}"))
            // 保存xml
            save(mvvmActivityXml(packageName, activityClass,isCreateViewModel,isUseDataBinding), resOut.resolve("layout/${layoutName}.xml"))
        }
    }


    if(isCreateViewModel){
        // 保存viewmodel
        save(mvvmViewModel(packageName, activityClass,isCreateRepository), srcOut.resolve("viewModel/${activityClass}ViewModel.${ktOrJavaExt}"))
    }

    if(isCreateRepository){
        // 保存repository
        save(mvvmRepository(packageName, activityClass), srcOut.resolve("repository/${activityClass}Repository.${ktOrJavaExt}"))
    }

}