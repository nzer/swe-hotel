<#import "parts/common.ftl" as c>

<@c.page>
<div class="container-fluid">
    <div class="row my-3 back">
        <button onclick="window.location.href='main'" class="btn btn-primary"><b>Назад</b></button>
    </div>
    <div class="row my-3 back">
        <a class="btn btn-primary" data-toggle="collapse" href="#merchant-form" role="button" aria-expanded="false" aria-controls="collapseExample">
            <b>Изменить профиль</b>
        </a>
    </div>
    <div class="back collapse <#if merchant??>show</#if>" id="merchant-form">
        <div class="form-group">
            <form method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-group row">
                        <label class="col-2 col-form-label">Phone number :</label>
                        <div class="col-2">
                            <input class="phone form-control ${(numberError??)?string('is-invalid', '')}" type="number" name="number"
                                   value="<#if merchant??>${merchant.number}</#if>"/>
                            <#if numberError??>
                            <div class="invalid-feedback">
                                ${numberError}
                            </div>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><b>Сохранить</b></button>
            </div>
        </div>
    </div>
</div>
</@c.page>