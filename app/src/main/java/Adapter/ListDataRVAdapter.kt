package Adapter

import Database.GlobalVar
import Interface.CardListener
import Model.User
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uc.firstappsprogtech.R
import com.uc.firstappsprogtech.databinding.CardUserBinding

class ListDataRVAdapter(val listUser: ArrayList<User>, val cardListener: CardListener):
    RecyclerView.Adapter<ListDataRVAdapter.viewHolder>() {

    class viewHolder (val itemView: View, val cardListener1: CardListener): RecyclerView.ViewHolder(itemView) {

        val binding = CardUserBinding.bind(itemView)

        fun setData(data: User){
            binding.namaCard.text = data.nama
            binding.emailCard.text = data.email
            binding.alamatCard.text = data.alamat
            if(data.imageString != "null") {
                val bArray = GlobalVar.StringToByteArr(data.imageString)

                val options = BitmapFactory.Options()
                options.inSampleSize = 2
                options.inScaled = true

                val bitMap = BitmapFactory.decodeByteArray(
                    bArray,
                    0,
                    bArray.size,
                    options
                )
                binding.pictureCard.setImageBitmap(bitMap)
            }
            itemView.setOnClickListener{
                Log.d("UserID", data.id.toString())
                cardListener1.onCardClick(data.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_user, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listUser[position])
    }

    override fun getItemCount(): Int {
        return listUser.size
    }


}