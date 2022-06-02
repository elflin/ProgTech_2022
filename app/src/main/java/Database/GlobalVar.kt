package Database

import Model.User
import android.util.Base64

class GlobalVar {
    companion object{
        val STORAGE_PERMISSION_CODE: Int = 100
        val listDataUser = ArrayList<User>()

        val ipAddress = "http://10.0.87.21/WebServicePT/"
        val ReadAll = ipAddress + "ReadAllUser.php"
        val ReadByID = ipAddress + "ReadUserByID.php"
        val CreateUser = ipAddress + "CreateUser.php"
        val UpdateUser = ipAddress + "UpdateUser.php"
        val DeleteUser = ipAddress + "DeleteUser.php"

        fun ByteArrToString(bArray: ByteArray): String {
            return Base64.encodeToString(bArray, Base64.DEFAULT)
        }

        fun StringToByteArr(raw: String):ByteArray{
            return Base64.decode(raw, Base64.DEFAULT)
        }
    }
}