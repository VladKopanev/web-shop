function setHiddenSortInput(fieldId, order) {
    document.getElementById(fieldId).value = fieldId.concat('_').concat(order)
}