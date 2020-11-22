<#import "parts/common.ftl" as c>

<@c.page>
    <div class="row">
        <div class="col-6">
            <p align="center"><b>Баланса Likecoin</b></p>
        </div>
        <div class="col-6">
            <p align="center"><b>E-money</b></p>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <p align="center">Доступно ${ltcBalance}</p>
        </div>
        <div class="col-6">
            <p align="center">Остаток вход ${ltcBalance}</p>
        </div>
    </div>
</@c.page>