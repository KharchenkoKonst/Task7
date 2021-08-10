package com.example.task7.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.task7.R
import com.example.task7.databinding.ActivityMainBinding
import com.example.task7.workmanager.TestWork
import java.util.concurrent.TimeUnit
/*
- Реализовать локальный поиск заметок в списке: поиск заметок осуществляется аналогично примеру
из семинара - запрос из SearchView привязан к ViewModel, фильтрация осуществляется запросом к БД (исп. LIKE).
В лекции говорилось об использовании специального поискового интента, но смысл этого я не понял.
Показать пример работы с Work Manager для бэкапа данных
(реальный бэкап делать необязательно, можно просто писать сообщение в лог).
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val navController by lazy {
        Navigation.findNavController(this, binding.navHost.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val workManager = com.example.task7.workmanager.WorkManager(applicationContext)
    }
}