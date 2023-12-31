package tech.leonam.estockando.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import tech.leonam.estockando.databinding.ActivitySearchBinding
import tech.leonam.estockando.view.adapt.SearchFragmentAdapter

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botaView.adapter = SearchFragmentAdapter(this)
        adicionarTabListener()
        arrastaProLado()
    }

    private fun arrastaProLado() {
        binding.botaView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tab.getTabAt(position)!!.select()
            }
        })
    }

    private fun adicionarTabListener() {
        binding.tab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.botaView.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }
}