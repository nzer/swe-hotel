<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container-fluid">
        <div class="row my-3 back">
            <button onclick="window.location.href='main'" class="btn btn-primary"><b>Назад</b></button>
        </div>
        <div class="row my-3 back">
            <button class="btn btn-primary" data-toggle="modal" href="#coupon-form" role="button" aria-expanded="false" aria-controls="collapseExample"><b>Выпуск купонов</b></button>
        </div>
        <div class="modal fade" id="coupon-form" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Форма выпуска купонов</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <form method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="text" name="name" class="form-control ${(nameError??)?string('is-invalid', '')}"
                                           value="<#if couponIssue??>${couponIssue.name}</#if>" placeholder="Название акции" />
                                    <#if nameError??>
                                    <div class="invalid-feedback">
                                        ${nameError}
                                    </div>
                                    </#if>
                                </div>
                                <div class="form-group">
                                    <input type="number" name="issued" class="form-control ${(issuedError??)?string('is-invalid', '')}"
                                           value="<#if couponIssue??>${couponIssue.issued}</#if>" placeholder="Число купонов" />
                                    <#if issuedError??>
                                    <div class="invalid-feedback">
                                        ${issuedError}
                                    </div>
                                    </#if>
                                </div>
                                <div class="form-group">
                                    <select class="form-control" name="type">
                                        <option value="1">Прием</option>
                                        <option value="2">Выдача</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <input type="number" step="any" name="price" class="form-control ${(priceError??)?string('is-invalid', '')}" placeholder="Цена" />
                                    <#if priceError??>
                                    <div class="invalid-feedback">
                                        ${priceError}
                                    </div>
                                    </#if>
                                </div>
                                <div class="form-group">
                                    <input type="datetime-local" name="expiration" class="form-control" placeholder="Срок действия">
                                </div>
                                <div class="form-group">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary"><b>Отправить</b></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="table-list">
            <table class="table">
                <thead>
                <tr class="table-names">
                    <th scope="col">Название</th>
                    <th scope="col">Тип</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Выпущено</th>
                    <th scope="col">Куплено</th>
                    <th scope="col">Доход</th>
                </tr>
                </thead>
                <tbody>
                <#list couponIssues as couponIssue>
                <tr>
                    <td>${couponIssue.name}</td>
                    <td>${couponIssue.type}</td>
                    <td>${couponIssue.price}</td>
                    <td>${couponIssue.issued}</td>
                    <td>${couponIssue.bought}</td>
                    <td>${couponIssue.income}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>