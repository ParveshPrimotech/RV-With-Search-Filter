package com.example.myapp.ui.main.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.databinding.ActivitySignupBinding
import com.example.myapp.network.ApiConstant
import com.example.myapp.repository.MainRepository
import com.example.myapp.viewModel.ProductsViewModel
import com.example.myapp.viewModel.SignupViewModel
import com.example.myapp.viewModel.ViewModelFactory

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = ApiConstant.getInstance()
        val mainRepository = MainRepository(retrofitService)

        binding.mbSubmit.setOnClickListener {
            viewModel.setSignupData(
                binding.tvName.text.toString(),
                binding.tvEmail.text.toString(),
                binding.tvPassword.text.toString()
            )
        }
        viewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository))[SignupViewModel::class.java]

        viewModel.validationMsg.observe(this) {
            Toast.makeText(applicationContext, getString(it), Toast.LENGTH_SHORT).show()
        }
    }
}