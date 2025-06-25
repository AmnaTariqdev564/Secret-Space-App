package com.amna.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // UI elements
    private TextView questionText;
    private RadioGroup optionsGroup;
    private RadioButton option1, option2, option3, option4;
    private Button prevButton, nextButton, submitButton;

    // Quiz Data
    private String[] questions = {
            "What is the capital of Pakistan?",
            "Who is the founder of Pakistan?",
            "Which is the largest continent?",
            "How many colors are in the rainbow?",
            "What is the national animal of Pakistan?"
    };

    private String[][] options = {
            {"Karachi", "Lahore", "Islamabad", "Peshawar"},
            {"Allama Iqbal", "Quaid-e-Azam", "Sir Syed Ahmed Khan", "Liaquat Ali Khan"},
            {"Asia", "Africa", "Europe", "Australia"},
            {"5", "6", "7", "8"},
            {"Markhor", "Lion", "Tiger", "Elephant"}
    };

    private int[] correctAnswers = {2, 1, 0, 2, 0}; // Index of correct answers
    private int currentQuestion = 0; // Track current question
    private int[] userAnswers = new int[5]; // Track user's answers (-1 for unanswered)
    private int score = 0; // Track the score

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        submitButton = findViewById(R.id.submitButton);

        // Initialize userAnswers array
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = -1; // -1 means unanswered
        }

        // Load the first question
        loadQuestion();

        // Previous Button Click
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
                currentQuestion--;
                loadQuestion();
            }
        });

        // Next Button Click
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
                currentQuestion++;
                loadQuestion();
            }
        });

        // Submit Button Click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnswer();
                calculateScore();
                Toast.makeText(MainActivity.this, "Quiz Finished! Your Score: " + score, Toast.LENGTH_LONG).show();
                submitButton.setEnabled(false);
                nextButton.setEnabled(false);
                prevButton.setEnabled(false);
            }
        });
    }

    private void loadQuestion() {
        // Set question and options
        questionText.setText(questions[currentQuestion]);
        option1.setText(options[currentQuestion][0]);
        option2.setText(options[currentQuestion][1]);
        option3.setText(options[currentQuestion][2]);
        option4.setText(options[currentQuestion][3]);

        // Load previously selected answer
        optionsGroup.clearCheck();
        if (userAnswers[currentQuestion] != -1) {
            ((RadioButton) optionsGroup.getChildAt(userAnswers[currentQuestion])).setChecked(true);
        }

        // Enable/Disable navigation buttons
        prevButton.setEnabled(currentQuestion > 0);
        nextButton.setEnabled(currentQuestion < questions.length - 1);

        // Show Submit Button at the last question
        submitButton.setVisibility(currentQuestion == questions.length - 1 ? View.VISIBLE : View.GONE);
    }

    private void saveAnswer() {
        int selectedOptionId = optionsGroup.getCheckedRadioButtonId();
        if (selectedOptionId != -1) {
            int selectedIndex = optionsGroup.indexOfChild(findViewById(selectedOptionId));
            userAnswers[currentQuestion] = selectedIndex;
        }
    }

    private void calculateScore() {
        score = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
    }
}