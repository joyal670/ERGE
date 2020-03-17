package com.example.erge.ApiModel;

public class PaymentResponseModel {

    /**
     * id : ch_1FUraDKAyvTf9zQXALTObaul
     * object : charge
     * amount : 100
     * amount_refunded : 0
     * application : null
     * application_fee : null
     * application_fee_amount : null
     * balance_transaction : txn_1FUraEKAyvTf9zQX7Dh7WufP
     * billing_details : {"address":{"city":null,"country":null,"line1":null,"line2":null,"postal_code":null,"state":null},"email":null,"name":null,"phone":null}
     * captured : true
     * created : 1571390237
     * currency : usd
     * customer : cus_G0tMFrQt1678w0
     * description : null
     * destination : null
     * dispute : null
     * failure_code : null
     * failure_message : null
     * fraud_details : {}
     * invoice : null
     * livemode : false
     * metadata : {}
     * on_behalf_of : null
     * order : null
     * outcome : {"network_status":"approved_by_network","reason":null,"risk_level":"normal","risk_score":20,"seller_message":"Payment complete.","type":"authorized"}
     * paid : true
     * payment_intent : null
     * payment_method : card_1FUraDKAyvTf9zQXWa1eySHh
     * payment_method_details : {"card":{"brand":"visa","checks":{"address_line1_check":null,"address_postal_code_check":null,"cvc_check":"pass"},"country":"US","exp_month":12,"exp_year":2020,"fingerprint":"0H1dHhJmYZALGKsT","funding":"credit","installments":null,"last4":"4242","network":"visa","three_d_secure":null,"wallet":null},"type":"card"}
     * receipt_email : ajeeshmnmd@gmail.com
     * receipt_number : null
     * receipt_url : https://pay.stripe.com/receipts/acct_1B7HUhKAyvTf9zQX/ch_1FUraDKAyvTf9zQXALTObaul/rcpt_G0tMQxottWFI9sUOSM4UfkMwYl5AbEK
     * refunded : false
     * refunds : {"object":"list","data":[],"has_more":false,"total_count":0,"url":"/v1/charges/ch_1FUraDKAyvTf9zQXALTObaul/refunds"}
     * review : null
     * shipping : null
     * source : {"id":"card_1FUraDKAyvTf9zQXWa1eySHh","object":"card","address_city":null,"address_country":null,"address_line1":null,"address_line1_check":null,"address_line2":null,"address_state":null,"address_zip":null,"address_zip_check":null,"brand":"Visa","country":"US","customer":"cus_G0tMFrQt1678w0","cvc_check":"pass","dynamic_last4":null,"exp_month":12,"exp_year":2020,"fingerprint":"0H1dHhJmYZALGKsT","funding":"credit","last4":"4242","metadata":{},"name":null,"tokenization_method":null}
     * source_transfer : null
     * statement_descriptor : null
     * statement_descriptor_suffix : null
     * status : succeeded
     * transfer_data : null
     * transfer_group : null
     */

    private String id;
    private boolean paid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }


}
