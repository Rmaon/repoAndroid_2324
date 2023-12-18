package com.ramon.fragmentsramon

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterViewPager(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{FragmentoA()}
            1->{FragmentoB()}
            2->{FragmentoC()}
            else->{throw Resources.NotFoundException("Posicion no encontrada")
            }
        }
    }

}