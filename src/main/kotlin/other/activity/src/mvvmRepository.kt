package other.activity.src

fun mvvmRepository(
    packageName:String,
    activityClass:String
)="""
package $packageName.repository
import com.tz.log.TzLogApi
import com.tz.network.api.TzNetworkApi

class ${activityClass}Repository: TzNetworkApi, TzLogApi {
    
}
"""