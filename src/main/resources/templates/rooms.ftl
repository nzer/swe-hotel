<#import "parts/common.ftl" as c>

<@c.page>
<div class="col-6">
    <h4>Add new</h4>
    <div id="season-form">
        <div class="form-group">
            <form method="post" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" name="number" class="form-control" placeholder="Number">
                    </div>
                    <div class="form-group">
                        <input type="number" name="floor" class="form-control" placeholder="Floor" />
                    </div>
                    <label>Hotel</label>
                    <div class="form-group">
                        <label>Hotel</label>
                        <select name="hotelId">
                            <#list hotels as h>
                            <option value="${h.id}">${h.name}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Hotel type</label>
                        <select name="hotelTypeId">
                            <#list hotelTypes as h>
                            <option value="${h.id}">${h.name}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><b>Сохранить</b></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
</@c.page>

