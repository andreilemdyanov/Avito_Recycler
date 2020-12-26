package ru.work.avitorecycler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.work.avitorecycler.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    FragmentItemList.newInstance(),
                    MOVIES_FRAGMENT_TAG
                )
                .commit()
        else supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT_TAG) as? FragmentItemList
    }

    companion object {
        const val MOVIES_FRAGMENT_TAG = "moviesFragment"
    }
}