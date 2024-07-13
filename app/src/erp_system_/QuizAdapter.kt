package com.example.erp_system_


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.erp_system_.R
import com.example.erp_system_.Question

class QuizAdapter(private var quizList: List<Question>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
        private val optionARadioButton: RadioButton = itemView.findViewById(R.id.optionARadioButton)
        private val optionBRadioButton: RadioButton = itemView.findViewById(R.id.optionBRadioButton)
        private val optionCRadioButton: RadioButton = itemView.findViewById(R.id.optionCRadioButton)
        private val optionDRadioButton: RadioButton = itemView.findViewById(R.id.optionDRadioButton)
        private val submitButton: Button = itemView.findViewById(R.id.submit_button)

        fun bind(question: Question) {
            questionTextView.text = question.question
            optionARadioButton.text = "A) ${question.optionA}"
            optionBRadioButton.text = "B) ${question.optionB}"
            optionCRadioButton.text = "C) ${question.optionC}"
            optionDRadioButton.text = "D) ${question.optionD}"

            // Reset background colors
            optionARadioButton.setBackgroundColor(Color.TRANSPARENT)
            optionBRadioButton.setBackgroundColor(Color.TRANSPARENT)
            optionCRadioButton.setBackgroundColor(Color.TRANSPARENT)
            optionDRadioButton.setBackgroundColor(Color.TRANSPARENT)

            // Set selected option if already answered
            when (question.selectedOption) {
                "A" -> optionARadioButton.isChecked = true
                "B" -> optionBRadioButton.isChecked = true
                "C" -> optionCRadioButton.isChecked = true
                "D" -> optionDRadioButton.isChecked = true
            }

            // Disable radio buttons after submission
            if (question.submitted) {
                optionARadioButton.isEnabled = false
                optionBRadioButton.isEnabled = false
                optionCRadioButton.isEnabled = false
                optionDRadioButton.isEnabled = false
                submitButton.isEnabled = false

                // Highlight correct and incorrect options
                when (question.selectedOption) {
                    question.correctOption -> {
                        when (question.selectedOption) {
                            "A" -> optionARadioButton.setBackgroundColor(Color.GREEN)
                            "B" -> optionBRadioButton.setBackgroundColor(Color.GREEN)
                            "C" -> optionCRadioButton.setBackgroundColor(Color.GREEN)
                            "D" -> optionDRadioButton.setBackgroundColor(Color.GREEN)
                        }
                    }
                    else -> {
                        when (question.selectedOption) {
                            "A" -> optionARadioButton.setBackgroundColor(Color.RED)
                            "B" -> optionBRadioButton.setBackgroundColor(Color.RED)
                            "C" -> optionCRadioButton.setBackgroundColor(Color.RED)
                            "D" -> optionDRadioButton.setBackgroundColor(Color.RED)
                        }
                        when (question.correctOption) {
                            "A" -> optionARadioButton.setBackgroundColor(Color.GREEN)
                            "B" -> optionBRadioButton.setBackgroundColor(Color.GREEN)
                            "C" -> optionCRadioButton.setBackgroundColor(Color.GREEN)
                            "D" -> optionDRadioButton.setBackgroundColor(Color.GREEN)
                        }
                    }
                }
            } else {
                // OnClickListener for radio buttons to update selected option
                optionARadioButton.setOnClickListener {
                    if (!question.submitted) {
                        question.selectedOption = "A"
                        notifyDataSetChanged()
                    }
                }
                optionBRadioButton.setOnClickListener {
                    if (!question.submitted) {
                        question.selectedOption = "B"
                        notifyDataSetChanged()
                    }
                }
                optionCRadioButton.setOnClickListener {
                    if (!question.submitted) {
                        question.selectedOption = "C"
                        notifyDataSetChanged()
                    }
                }
                optionDRadioButton.setOnClickListener {
                    if (!question.submitted) {
                        question.selectedOption = "D"
                        notifyDataSetChanged()
                    }
                }

                // OnClickListener for submit button
                submitButton.setOnClickListener {
                    question.submitted = true
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item_layout, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val question = quizList[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int {
        return quizList.size
    }

    fun updateData(newQuizList: List<Question>) {
        quizList = newQuizList
        notifyDataSetChanged()
    }
}
