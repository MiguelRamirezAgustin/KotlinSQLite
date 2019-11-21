package com.example.sqlite

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCustom ( items:ArrayList<Alumno>, var listener: ClickListener, var longClickListener: LongClickListener ): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {


    var items:ArrayList<Alumno>? = null
    var multiSeleccion = false

    var itemsSeleccionados:ArrayList<Int>? = null
    var viewHolder: ViewHolder? = null

    init {
        this.items = items
        itemsSeleccionados = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.template_alumno, parent , false)
        val viewHolder = ViewHolder(vista, listener , longClickListener)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)

        holder.nombre?.text = item?.nombre
        holder.id?.text = item?.id

        if(itemsSeleccionados?.contains(position)!!){
            holder.vista.setBackgroundColor(Color.LTGRAY)
        }else{
            holder.vista.setBackgroundColor(Color.WHITE)
        }
    }

    fun iniciarAction(){
        multiSeleccion = true
    }

    fun destruirAction(){
        multiSeleccion = false
        itemsSeleccionados?.clear()
        notifyDataSetChanged()
    }

    fun terminarActionMode(){
        for (item in itemsSeleccionados!!){
            itemsSeleccionados?.remove(item)
        }
        multiSeleccion = false
        notifyDataSetChanged()
    }

    fun seleccionarItems(index:Int){
        if(multiSeleccion){
            if(itemsSeleccionados?.contains(index)!!){
                itemsSeleccionados?.remove(index)
            }else{
                itemsSeleccionados?.add(index)
            }
            notifyDataSetChanged()
        }
    }

    fun  obtenerNumeroelementosSeleccionados():Int{
        return itemsSeleccionados?.count()!!
    }

    fun eliminarSeleccionados(){
        if(itemsSeleccionados?.count()!! > 0){
            var itemsEliminados = ArrayList<Alumno>()

            for (index in itemsSeleccionados!!){
                itemsEliminados.add(items?.get(index)!!)
            }

            items?.removeAll(itemsEliminados)
            itemsSeleccionados?.clear()
        }
    }


    class ViewHolder(vista: View, listener: ClickListener, longClickListener: LongClickListener):
        RecyclerView.ViewHolder(vista), View.OnClickListener , View.OnLongClickListener{


        var vista = vista
        var nombre: TextView? = null
        var id: TextView? = null

        //apunta a las ainterfase ClickListener
        var listener:ClickListener? = null
        var longListener:LongClickListener? = null

        init {
            nombre = vista.findViewById(R.id.tvNombre)
            id = vista.findViewById(R.id.tvid)

            this.listener = listener
            this.longListener = longClickListener

            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            //se implementa la interfase y pide dos parametros la vista y la position
            this.listener?.onClick(v!!, adapterPosition)
        }


        override fun onLongClick(v: View?): Boolean {
            this.longListener?.LongClick(v!!, adapterPosition)
            return true
        }

    }


}