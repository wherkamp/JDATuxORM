package me.kingtux.jdatuxorm;

import me.kingtux.tuxjsql.basic.sql.BasicDataTypes;
import me.kingtux.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxorm.TOConnection;
import me.kingtux.tuxorm.serializers.SingleSecondarySerializer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class GuildSerializer implements SingleSecondarySerializer<Guild, String> {
    private final TOConnection connection;
    private final JDA jda;

    public GuildSerializer(TOConnection toConnection, JDA jda) {
        this.connection = toConnection;
        this.jda = jda;
    }

    @Override
    public String getSimplifiedValue(Guild o) {
        return o.getId();
    }

    @Override
    public Guild buildFromSimplifiedValue(String value) {
        return jda.getGuildById(value);
    }

    @Override
    public ColumnBuilder createColumn(String name) {
        return connection.getBuilder().createColumn().name(name).setDataType(BasicDataTypes.TEXT);
    }
}