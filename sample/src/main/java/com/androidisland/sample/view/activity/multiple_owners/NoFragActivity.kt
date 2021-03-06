package com.androidisland.sample.view.activity.multiple_owners

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.androidisland.sample.Constants
import com.androidisland.sample.R
import com.androidisland.sample.openColorDialog
import com.androidisland.sample.viewmodel.ColorViewModel
import com.androidisland.vita.VitaOwner
import com.androidisland.vita.vita
import kotlinx.android.synthetic.main.activity_no_frag.*

class NoFragActivity : AppCompatActivity() {

    private val viewModel by lazy {
        vita.with(VitaOwner.Multiple(this)).getViewModel<ColorViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_frag)
        viewModel.observeColor(this, Observer {
            Log.d(Constants.TAG, "NoFragActivity, Color changed")
            main_group.setBackgroundColor(it)
        })
        main_group.setOnClickListener {
            openColorDialog("NoFragActivity") {
                changeColor(it)
            }
        }
    }

    private fun changeColor(color: Int) = viewModel.setColor(color)

}
