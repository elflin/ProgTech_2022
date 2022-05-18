package Adapter

import Model.User
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uc.firstappsprogtech.R
import com.uc.firstappsprogtech.databinding.CardUserBinding

class ListDataRVAdapter(val listUser: ArrayList<User>):
    RecyclerView.Adapter<ListDataRVAdapter.viewHolder>() {

    class viewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        //Bind
        val binding = CardUserBinding.bind(itemView)

        // Ext
//        val nama_card = itemView.namaCard
//        val email_card = itemView.emailCard
//        val alamat_card = itemView.alamat_card
//        val picture_card = itemView.picture_card

        fun setData(data: User){
            binding.namaCard.text = data.nama
            binding.emailCard.text = data.email
            binding.alamatCard.text = data.alamat
            if(data.imageUri.isNotEmpty())
                binding.pictureCard.setImageURI(Uri.parse(data.imageUri))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_user, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listUser[position])
    }

    override fun getItemCount(): Int {
        return listUser.size
    }


}