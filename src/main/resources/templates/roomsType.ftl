<#import "parts/common.ftl" as c>

<@c.page>
    <div class="row">
        <div class="col-6">
            <h4>Rooms list</h4>
            <#list types as t>
                <div class="adminCard">
                    <p>Name: ${t.name}</p>
                    <p>Capacity: ${t.capacity}</p>
                    <p>Size: ${t.size}</p>
                </div>
            </#list>
        </div>
        <div class="col-6">
            <h4>Add new</h4>
            <div id="season-form">
                <div class="form-group">
                    <form method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="name" class="form-control" placeholder="Name">
                            </div>
                            <div class="form-group">
                                <input type="number" name="size" class="form-control" placeholder="Size">
                            </div>
                            <div class="form-group">
                                <input type="number" name="capacity" class="form-control" placeholder="Capacity">
                            </div>
                            <div class="form-group">
                                <label>Hotel</label>
                                <select name="hotelId">
                                    <#list hotels as h>
                                        <option value="${h.id}">${h.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Features</label>
                                <select multiple name="featureId">
                                    <#list features as f>
                                        <option value="${f.id}">${f.name}</option>
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

