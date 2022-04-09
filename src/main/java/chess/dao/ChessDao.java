package chess.dao;

import chess.domain.state.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessDao {

    public void saveState(State state) {
        Connection connection = Connector.getConnection();
        final String sql = "insert into chess_game (state) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateState(State state) {
        Connection connection = Connector.getConnection();
        final String sql = "update chess_game set state = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, state.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getState() {
        Connection connection = Connector.getConnection();
        final String sql = "select state from chess_game";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getString("state");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll() {
        Connection connection = Connector.getConnection();
        final String sql = "truncate table chess_game";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
