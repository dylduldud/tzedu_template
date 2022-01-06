package other.item.src


fun itemTemplate(
    applicationPackage: String,
    activityClass: String,
    layoutName: String,
    packageName: String,
    isCreateDataBinding: Boolean
): String {
    if (isCreateDataBinding) {
        return """
package $packageName.item

import android.content.Context
import android.view.View
import android.view.ViewGroup
import $applicationPackage.R
import $applicationPackage.databinding.Item${activityClass}Binding
import com.tz.dazzle.Item
import com.tz.tzbaselib.impl.itemCreateDataBinding
import com.tz.tzbaselib.impl.itemNotifyDataBinding

class ${activityClass}Item : Item<${activityClass}Model>() {

    override fun itemCreate(context: Context, parent: ViewGroup): View {
        return itemCreateDataBinding<Item${activityClass}Binding>(context, parent, R.layout.$layoutName)
    }

    override fun itemBuild(item: View?, index: Int, data: ${activityClass}Model) {
        itemNotifyDataBinding<Item${activityClass}Binding>(item ?: return)?.model = data

    }

}

class ${activityClass}Model(

)
"""
    }
    return """
package $packageName.item

import android.content.Context
import android.view.View
import android.view.ViewGroup
import $applicationPackage.R
import com.tz.dazzle.Item

class ${activityClass}Item : Item<${activityClass}Model>() {

    override fun itemCreate(context: Context, parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.${layoutName}, parent, false)
    }

    override fun itemBuild(item: View?, index: Int, data: ${activityClass}Model) {
       
    }

}

class ${activityClass}Model(

)
    """
}