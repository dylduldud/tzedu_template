package other

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

class TzeduPluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        tzActivityTemplate,
        tzItemTemplate
    )
}

val tzActivityTemplate
    get() = template {

        name = "TzMvvmTemplate"
        description = "潭州Mvvm架构基础模板"

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        lateinit var layoutName: StringParameter

        val activityClass = stringParameter {
            name = "Name"
            default = "Main"
            help = "只输入名字，不要包含后缀"
            constraints = listOf(Constraint.NONEMPTY)

        }
        val createView = booleanParameter {
            name = "创建View层"
            default = true
            help = "是否创建View层"
        }
        val enumCheckActOrFragment = enumParameter<EType> {
            name = "请选择View的类型"
            default = EType.Activity
            visible = { createView.value }
        }

        layoutName = stringParameter {
            name = "Layout Name"
            default = "activity_main"
            help = "请输入布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = {
                if (enumCheckActOrFragment.value == EType.Activity) activityToLayout(activityClass.value) else fragmentToLayout(
                    activityClass.value
                )
            }
            visible = { createView.value }
        }

        val packageName = defaultPackageNameParameter

        val createDataBinding = booleanParameter {
            name = "使用DataBinding"
            default = true
            help = "是否使用DataBinding"
            visible = { createView.value }
        }

        val createViewModel = booleanParameter {
            name = "创建ViewModel层"
            default = true
            help = "是否创建ViewModel层"
        }
        val createRepository = booleanParameter {
            name = "创建Repository层"
            default = true
            help = "是否创建Repository层"
        }

        widgets(
            TextFieldWidget(activityClass),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            EnumWidget(enumCheckActOrFragment),
            CheckBoxWidget(createView),
            CheckBoxWidget(createViewModel),
            CheckBoxWidget(createRepository),
            CheckBoxWidget(createDataBinding),
        )
//        thumb { File("logo.png") }
        recipe = { data: TemplateData ->
            mvvmActivityRecipe(
                data as ModuleTemplateData,
                activityClass.value,
                layoutName.value,
                packageName.value,
                enumCheckActOrFragment.value,
                createView.value,
                createViewModel.value,
                createRepository.value,
                createDataBinding.value
            )
        }
    }


val tzItemTemplate
    get() = template {

        name = "TzItemTemplate"
        description = "潭州Item模板"

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        lateinit var layoutName: StringParameter

        val activityClass = stringParameter {
            name = "Item Name"
            default = ""
            help = "只输入名字，不要包含后缀"
            constraints = listOf(Constraint.NONEMPTY,Constraint.CLASS,Constraint.UNIQUE)

        }

        layoutName = stringParameter {
            name = "Layout Name"
            default = "item_"
            help = "请输入Item布局的名字"
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = {
                activityToLayout(activityClass.value).replaceFirst("activity", "item")
            }
        }

        val packageName = defaultPackageNameParameter

        val createDataBinding = booleanParameter {
            name = "使用DataBinding"
            default = true
            help = "是否使用DataBinding"
        }


        widgets(
            TextFieldWidget(activityClass),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageName),
            CheckBoxWidget(createDataBinding),
        )

        recipe = { data: TemplateData ->
            itemRecipe(
                data as ModuleTemplateData,
                activityClass.value,
                layoutName.value,
                packageName.value,
                createDataBinding.value
            )
        }
    }
val defaultPackageNameParameter
    get() = stringParameter {
        name = "包名"
        visible = { !isNewModule }
        default = "com.company.app"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }

enum class EType {
    Activity, Fragment
}


