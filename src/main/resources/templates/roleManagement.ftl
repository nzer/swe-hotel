<#import "parts/common.ftl" as c>

<@c.page>
<div class="container-fluid">
    <div class="row my-3 back">
        <button onclick="window.location.href='main'" class="btn btn-primary"><b>Назад</b></button>
    </div>
    <div class="row my-3 back">
        <a class="btn btn-primary" data-toggle="collapse" href="#userPhoneNumber-form" role="button" aria-expanded="false" aria-controls="collapseExample">
            <b>Добавить сотрудников</b>
        </a>
    </div>
    <div class="back collapse <#if userError??>show</#if>" id="userPhoneNumber-form">
        <div class="form-group">
            <form action="?bar2" method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="number" name="userPhoneNumber" class="form-control ${(userError??)?string('is-invalid', '')}"
                               placeholder="Номер телефона сотрудника" />
                        <#if userError??>
                        <div class="invalid-feedback">
                            ${userError}
                        </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><b>Сохранить</b></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row mt-3 table-names">
        <div class="col-2">
            <p align="center">Сотрудники</p>
        </div>
        <div class="col-2">
            <p align="center">Роль</p>
        </div>
    </div>
    <#list workers as worker>
    <form action="?bar3" class="mt-3" method="post" enctype="multipart/form-data">
        <div class="form-row">
            <div class="col-2">
                <p align="center">${worker.username}</p>
                <input type="hidden" name="username" value="${worker.username}"/>
            </div>
            <div class="col-2">
                <p align="center">${worker.roleToString}</p>
            </div>
            <div class="col-4">
                <div id="myProgress">
                    <div class="myBar" id="${worker.coinDistribution}"></div>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="col-2"></div>
            <div class="col-2">
                <div class="row">
                    <button type="submit" name="action" value="minus" class="btn btn-primary"><b>-</b></button>
                    <p class="mx-2" align="center">${worker.coinDistribution}</p>
                    <button type="submit" name="action" value="plus" class="btn btn-primary"><b>+</b></button>
                </div>
            </div>
        </div>
    </form>
    </#list>

</div>
</@c.page>