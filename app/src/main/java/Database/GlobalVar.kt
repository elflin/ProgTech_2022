package Database

import Model.User
import android.util.Base64
import kotlin.collections.ArrayList

class GlobalVar {
    companion object{
        val STORAGE_PERMISSION_CODE: Int = 100
        val listDataUser = ArrayList<User>()
        val ipAddress = "http://192.168.18.8/progTech_webservice_trial/"
        val InsertData = ipAddress + "InsertDatadiri.php"
        val ReadAllData = ipAddress + "ReadAllDatadiri.php"
        val ReadByIdData = ipAddress + "ReadDatadiriByID.php"
        val UpdateData = ipAddress + "UpdateDatadiri.php"
        val DeleteData = ipAddress + "DeleteDatadiri.php"

        fun ByteArrToString(bArray: ByteArray): String {
            return Base64.encodeToString(bArray, Base64.DEFAULT)
        }

        fun StringToByteArr(raw: String):ByteArray{
            return Base64.decode(raw, Base64.DEFAULT)
        }
    }
}