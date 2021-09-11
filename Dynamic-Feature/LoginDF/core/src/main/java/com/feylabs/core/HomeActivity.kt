package com.feylabs.core

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.feylabs.core.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sesi = SessionManager(this)
        userRepository = UserRepository.getInstance(sesi)

        binding.tvWelcome.text = "Welcome ${userRepository.getUser()}"

        binding.btnLogout.setOnClickListener {
            userRepository.logoutUser()
            moveToMainActivity()
        }

        binding.btnChat.setOnClickListener {
            try {
//                installChatModule()
                moveToChatActivity()
            } catch (e: Exception){
                Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun moveToChatActivity() {
        startActivity(Intent(this, Class.forName("com.dicoding.mysimplelogin.chat.ChatActivity")))
    }

//    private fun installChatModule() {
//        val splitInstallManager = SplitInstallManagerFactory.create(this)
//        val moduleChat = "chat"
//        if (splitInstallManager.installedModules.contains(moduleChat)) {
//            moveToChatActivity()
//            Toast.makeText(this, "Open module", Toast.LENGTH_SHORT).show()
//        } else {
//            val request = SplitInstallRequest.newBuilder()
//                .addModule(moduleChat)
//                .build()
//            splitInstallManager.startInstall(request)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "Success installing module", Toast.LENGTH_SHORT).show()
//                    moveToChatActivity()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(this, "Error installing module", Toast.LENGTH_SHORT).show()
//                }
//        }
//    }

    private fun moveToMainActivity() {
        var intent: Intent? = null
        try {
            intent = Intent(
                this,
                Class.forName("com.dicoding.mysimplelogin.MainActivity")
            )
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            Log.d("taga","ora ono clasnya")
            e.printStackTrace()
        }

//        startActivity(Intent(this, Class.forName("com.dicoding.home.MainActivity")))
        finish()
    }
}
