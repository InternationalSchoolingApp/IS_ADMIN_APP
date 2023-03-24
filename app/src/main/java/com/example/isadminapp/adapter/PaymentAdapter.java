package com.example.isadminapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.isadminapp.R;
import com.example.isadminapp.model.PaymentSearchModel;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    public List<PaymentSearchModel.AdvancePaymentSearchResponseDTO> list ;

    private int visible = 0;

    public PaymentAdapter(List<PaymentSearchModel.AdvancePaymentSearchResponseDTO> list) {
        this.list = list;
    }



    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.payment_adapter_layout, parent, false);
        PaymentAdapter.PaymentViewHolder paymentViewHolder = new PaymentAdapter.PaymentViewHolder(view);
        return paymentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {


        holder.studentName.setText(""+list.get(position).getStudentName());
        holder.studentGrade.setText(""+list.get(position).getGradeName());
        holder.paymentName.setText(""+list.get(position).getPaymentName());
        holder.paymentAmount.setText(""+list.get(position).getPayAmount());
        holder.paymentScheduleDate.setText(""+list.get(position).getScheduledPayDate());
        holder.paymentDate.setText(""+list.get(position).getPayDate());
        holder.paymentStatus.setText(""+list.get(position).getPaymentStatus());
        holder.paymentDetailId.setText(""+list.get(position).getUserPaymentDetailsId());
        holder.studentEmail.setText(""+list.get(position).getStudentEmail());
        holder.learningProgram.setText(""+list.get(position).getRegistrationType());
        holder.planName.setText(""+list.get(position).getPlanName());
        holder.paymentTitle.setText(""+list.get(position).getPaymentTitle());
        holder.paymentAdditionalAmount.setText(""+list.get(position).getAdditionalPayment());
        holder.paymentGateWayTool.setText(""+list.get(position).getPgName());
        holder.ExpandButton.setOnClickListener(v->{

            if (visible ==0){
                holder.hidelayout.setVisibility(v.VISIBLE);
                visible = 1;
                holder.ExpandButton.setText("Minimize");
            }else {
                holder.hidelayout.setVisibility(v.GONE);
                visible = 0;
                holder.ExpandButton.setText("Expand");
            }

        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {

        TextView studentName, studentGrade, paymentName, paymentAmount, paymentScheduleDate,
                paymentDate, paymentStatus, paymentDetailId, studentEmail, learningProgram,

                planName, paymentTitle, paymentAdditionalAmount, paymentGateWayTool;
        LinearLayout hidelayout;

        RelativeLayout layout;

        Button ExpandButton;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            planName = itemView.findViewById(R.id.payment_plan_name);
            paymentTitle = itemView.findViewById(R.id.payment_title);
            paymentAdditionalAmount = itemView.findViewById(R.id.payment_additional_amount);
            paymentGateWayTool = itemView.findViewById(R.id.payments_gateway_tool);
            learningProgram = itemView.findViewById(R.id.payment_learning_program);
            studentName = itemView.findViewById(R.id.payment_student_name);
            studentGrade = itemView.findViewById(R.id.payment_student_grade);
            paymentName = itemView.findViewById(R.id.payment_name);
            paymentAmount = itemView.findViewById(R.id.payment_amount);
            paymentScheduleDate = itemView.findViewById(R.id.payment_schedule_date);
            paymentStatus = itemView.findViewById(R.id.payment_status);
            paymentDate = itemView.findViewById(R.id.payment_date);
            paymentDetailId = itemView.findViewById(R.id.payment_id);
            studentEmail = itemView.findViewById(R.id.payment_student_email);
            ExpandButton = itemView.findViewById(R.id.button);
            hidelayout = itemView.findViewById(R.id.payment_hide);
            layout = itemView.findViewById(R.id.fullpaymentLayout);


        }
    }
}
