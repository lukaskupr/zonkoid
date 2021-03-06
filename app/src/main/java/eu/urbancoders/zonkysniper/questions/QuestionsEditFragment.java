package eu.urbancoders.zonkysniper.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import eu.urbancoders.zonkysniper.LoanDetailsActivity;
import eu.urbancoders.zonkysniper.R;
import eu.urbancoders.zonkysniper.core.ZSFragment;
import eu.urbancoders.zonkysniper.dataobjects.Loan;
import eu.urbancoders.zonkysniper.dataobjects.Question;
import eu.urbancoders.zonkysniper.events.SendQuestion;
import org.greenrobot.eventbus.EventBus;

/**
 * Author: Ondrej Steger (ondrej@steger.cz)
 * Copyright 2019
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
public class QuestionsEditFragment extends ZSFragment {

    Loan loan;
    Question question;

    public static QuestionsEditFragment newInstance(Question question, Loan loan) {
        QuestionsEditFragment fragment = new QuestionsEditFragment();
        Bundle args = new Bundle();
        if(question == null) {
            question = new Question();
        }
        args.putSerializable("question", question);
        args.putSerializable("loan", loan);
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionsEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_questions_edit, container, false);
        loan = (Loan) getArguments().getSerializable("loan");
        question = (Question) getArguments().getSerializable("question");

        getActivity().findViewById(R.id.fab).setVisibility(View.INVISIBLE);

        TextView questionTitle = rootView.findViewById(R.id.messages_title);

        final EditText questionText = rootView.findViewById(R.id.questionText);
        questionText.setHint(loan.getNickName());
        if(question.getMessage() != null && !question.getMessage().isEmpty()) {
            /**
             * editujeme otazku
             */
            questionTitle.setText(R.string.question_edit);
            questionText.setText(question.getMessage());
        }

        Button cancel = rootView.findViewById(R.id.buttonCancelQuestionEdit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoanDetailsActivity)getActivity()).fab.setVisibility(View.VISIBLE);
                getActivity().onBackPressed();
            }
        });

        Button send = rootView.findViewById(R.id.buttonSendQuestion);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question.setMessage(questionText.getText().toString());
                EventBus.getDefault().post(new SendQuestion.Request(loan.getId(), question));
                ((LoanDetailsActivity) getActivity()).fab.setVisibility(View.VISIBLE);
                getActivity().onBackPressed();
            }
        });



        return rootView;
    }
}
