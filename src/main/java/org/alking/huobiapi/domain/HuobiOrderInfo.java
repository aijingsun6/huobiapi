package org.alking.huobiapi.domain;

import com.google.gson.annotations.SerializedName;

public class HuobiOrderInfo {

    public static final String STATE_SUBMITTED = "submitted";

    public static final String STATE_PARTIAL_FILLED = "partial-filled";

    public static final String STATE_PARTIAL_CANCELED = "partial-canceled";

    public static final String STATE_FILLED = "filled";

    public static final String SATE_CANCELED = "canceled";

    @SerializedName("account-id")
    private long accountId;

    @SerializedName("amount")
    private String amount;

    /**
     * 订单撤销时间
     */
    @SerializedName("canceled-at")
    private long canceledAt;

    /**
     * 订单创建时间
     */
    @SerializedName("created-at")
    private long createdAt;

    /**
     * 已成交数量
     */
    @SerializedName("field-amount")
    private String fieldAmount;

    /**
     * 已成交总金额
     */
    @SerializedName("field-cash-amount")
    private String fieldCashAmount;

    @SerializedName("field-fees")
    private String fieldFees;

    @SerializedName("finished-at")
    private long finishedAt;

    @SerializedName("id")
    private long id;

    @SerializedName("price")
    private String price;

    @SerializedName("source")
    private String source;

    /**
     * <pre>
     *     pre-submitted 准备提交,
     *     submitting , submitted 已提交,
     *     partial-filled 部分成交,
     *     partial-canceled 部分成交撤销,
     *     filled 完全成交,
     *     canceled 已撤销
     * </pre>
     */
    @SerializedName("state")
    private String state;

    @SerializedName("symbol")
    private String symbol;

    /**
     * <pre>
     *     buy-market：市价买,
     *     sell-market：市价卖,
     *     buy-limit：限价买,
     *     sell-limit：限价卖
     * </pre>
     */
    @SerializedName("type")
    private String type;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(long canceledAt) {
        this.canceledAt = canceledAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getFieldAmount() {
        return fieldAmount;
    }

    public void setFieldAmount(String fieldAmount) {
        this.fieldAmount = fieldAmount;
    }

    public String getFieldCashAmount() {
        return fieldCashAmount;
    }

    public void setFieldCashAmount(String fieldCashAmount) {
        this.fieldCashAmount = fieldCashAmount;
    }

    public String getFieldFees() {
        return fieldFees;
    }

    public void setFieldFees(String fieldFees) {
        this.fieldFees = fieldFees;
    }

    public long getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(long finishedAt) {
        this.finishedAt = finishedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HuobiOrderInfo() {
    }
}
