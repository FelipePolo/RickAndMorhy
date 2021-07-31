package com.felipepolo.pruebaenvivo.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.felipepolo.pruebaenvivo.R
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import com.felipepolo.pruebaenvivo.databinding.TemplateCharactersRowBinding
import com.felipepolo.pruebaenvivo.utils.BaseViewHolder

class CharacterAdapter(
    private val context: Context,
    private val itemClickListener: OnCharacterClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var characterList: MutableList<CharactersEntity> = mutableListOf()

    interface OnCharacterClickListener {
        fun onCharacterLikeClick(character: CharactersEntity, position: Int)
        fun onCharacterDeleteClick(character: CharactersEntity, position: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacterList(characterList: List<CharactersEntity>) {
        this.characterList = characterList.toMutableList()
        notifyDataSetChanged()
    }

    fun addToCharacterList(characterList: List<CharactersEntity>) {
        val currentSize = characterList.size
        this.characterList.addAll(characterList)
        notifyItemRangeChanged(currentSize, characterList.size)
    }

    fun removeToCharacterList(character: CharactersEntity) {
        val itemPost = characterList.indexOf(character)
        characterList.remove(character)
        notifyItemRemoved(itemPost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = TemplateCharactersRowBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return CharactersViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CharactersViewHolder -> {
                holder.bind(characterList[position])
                if (characterList[position].favorite){
                    holder.binding.ivBtEvent.setImageResource(R.drawable.ic_delete)
                    holder.binding.btEvent.setOnClickListener {
                        itemClickListener.onCharacterDeleteClick(characterList[holder.adapterPosition],holder.adapterPosition)
                    }
                }else{
                    holder.binding.ivBtEvent.setImageResource(R.drawable.ic_start)
                    holder.binding.btEvent.setOnClickListener {
                        itemClickListener.onCharacterLikeClick(characterList[holder.adapterPosition],holder.adapterPosition)
                    }
                }

            }
        }
    }

    private inner class CharactersViewHolder(
        val binding: TemplateCharactersRowBinding
    ) : BaseViewHolder<CharactersEntity>(binding.root) {
        override fun bind(item: CharactersEntity) = with(binding) {
            Glide.with(context)
                .load(item.image)
                .centerCrop()
                .into(ivCharacter)
            tvSpecie.text = item.species
            tvName.text = item.name
            tvGender.text = item.gender
            tvStatus.text = item.status
        }
    }
}