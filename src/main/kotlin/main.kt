fun main() {

    print(commissionCount(MIR,1_500_000,2158964))
}

const val LIMIT_CARD_MONTHLY = 60_000_000
const val LIMIT_CARD_DAYLY = 15_000_000
const val LIMIT_VK_PAY_DAYLY = 1_500_000
const val LIMIT_VK_PAY_MONTHLY = 4_000_000
const val MIN_COMMISSION_VISA_MIR = 35_00

const val VK_PAY = "VK_PAY"
const val MAESTRO = "Maestro"
const val MASTERCARD = "Mastercard"
const val VISA = "Visa"
const val MIR = "Мир"

fun commissionCountMaestroMastercard(transactionAmount: Long, previousAmountMonthly: Long): String {
    var toCount = ((transactionAmount / 100 * 0.6) + 2_000).toInt()
    if (previousAmountMonthly >= LIMIT_CARD_MONTHLY) {
        return "Слишком много переводов!"
    } else if (transactionAmount >= LIMIT_CARD_DAYLY) {
        return "Слишком большая сумма!"
    } else if (transactionAmount >= 30_000 && transactionAmount <= 7_500_000) {
        return "Комиссия составляет 0 коп."
    } else {
        return "Комиссия составляет " + toCount.toString() + " копеек."
    }
}

fun commissionVisaMir(transactionAmount: Long, previousAmountMonthly: Long): String {
    var toCount = (transactionAmount / 100 * 0.75).toInt()
    if (previousAmountMonthly >= LIMIT_CARD_MONTHLY) {
        return "Слишком много переводов!"
    } else if (transactionAmount >= LIMIT_CARD_DAYLY) {
        return "Слишком большая сумма!"
    } else if (toCount < MIN_COMMISSION_VISA_MIR) {
        return "Комиссия составялет 35 руб."
    } else {
        return "Комиссия составляет " + toCount.toString() + " копеек."
    }
}

fun commissionVkPay(transactionAmount: Long, previousAmountMonthly: Long): String {
    if (previousAmountMonthly >= LIMIT_VK_PAY_MONTHLY) {
        return "Слишком много переводов!"
    } else if (transactionAmount >= LIMIT_VK_PAY_DAYLY) {
        return "Слишком большая сумма!"
    } else {
        return "Комиссия составляет 0 копеек."
    }
}

fun commissionCount(
    card_type: String = VK_PAY,
    previousAmountMonthly: Long = 0,
    transactionAmount: Long
): String {
    return when (card_type) {
        MAESTRO -> commissionCountMaestroMastercard(transactionAmount, previousAmountMonthly)
        MASTERCARD -> commissionCountMaestroMastercard(transactionAmount, previousAmountMonthly)
        VISA -> commissionVisaMir(transactionAmount, previousAmountMonthly)
        MIR -> commissionVisaMir(transactionAmount, previousAmountMonthly)
        else -> commissionVkPay(transactionAmount, previousAmountMonthly)
    }
}