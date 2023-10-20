package com.example.barbershop.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.example.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->


            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if (dayOfMonth < 10) {
                dia = "0$dayOfMonth"
            }
            if (monthOfYear < 10) {
                mes = "" + (monthOfYear + 1)
            } else {
                mes = (monthOfYear + 1).toString()
            }

            data = "$dia / $mes / $year"
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val minuto: String

            if (minute < 10) {
                minuto = "0$minute"
            } else {
                minuto = minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }
        binding.timePicker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener {

            val barbeiro1 = binding.barbeiro1
            val barbeiro2 = binding.barbeiro2
            val barbeiro3 = binding.barbeiro3

            if (hora.isEmpty()) {
                mensagem(it, "Preencha o horário!", "#FF0000")
            } else {
                val horaFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                val horaInicio = horaFormat.parse("08:00")
                val horaFim = horaFormat.parse("19:00")
                val horaSelecionada = horaFormat.parse(hora)

                if (horaSelecionada.before(horaInicio) || horaSelecionada.after(horaFim)) {
                    mensagem(it, "Barbearia fechada - horário de atendimento das 08:00 às 19:00!", "#FF0000")
                } else if (data.isEmpty()) {
                    mensagem(it, "Coloque uma data!", "#FF0000")
                } else if (barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty()) {
                    mensagem(it, "Agendamento realizado com sucesso!", "#FF03DAC5")
                } else if (barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty()) {
                    mensagem(it, "Agendamento realizado com sucesso!", "#FF03DAC5")
                } else if (barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty()) {
                    mensagem(it, "Agendamento realizado com sucesso!", "#FF03DAC5")
                } else {
                    mensagem(it, "Escolha um barbeiro!", "#FF0000")
                }
            }
         }
    }

    private fun mensagem(view: View, mensagem: String, cor:String) {
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }
}