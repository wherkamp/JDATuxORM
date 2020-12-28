package me.kingtux.jdatuxorm;

import me.kingtux.tuxjsql.basic.sql.BasicDataTypes;
import me.kingtux.tuxjsql.core.builders.ColumnBuilder;
import me.kingtux.tuxorm.TOConnection;
import me.kingtux.tuxorm.serializers.SingleSecondarySerializer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.User;

public class ChannelSerializer implements SingleSecondarySerializer<GuildChannel, String> {
    private final TOConnection connection;
    private final JDA jda;

    public ChannelSerializer(TOConnection toConnection, JDA jda) {
        this.connection = toConnection;
        this.jda = jda;
    }

    @Override
    public String getSimplifiedValue(GuildChannel o) {
        return o.getId();
    }

    @Override
    public GuildChannel buildFromSimplifiedValue(String value) {
        return jda.getGuildChannelById(value);
    }

    @Override
    public ColumnBuilder createColumn(String name) {
        return connection.getBuilder().createColumn().name(name).setDataType(BasicDataTypes.TEXT);
    }
}