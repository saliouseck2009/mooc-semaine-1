package controller.user;

import java.sql.*;
public class UserDaoImpl implements UserDao{

        static {
            try {
                Class.forName("org.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new Error(e);
            }
        }

        protected Connection conn;
        public UserDaoImpl( String userFilePath ) throws SQLException {

            String jdbcUrl = "jdbc:mysql://localhost:3306/mooc";
            // TODO : complete JDBC URL and initialize a connection.
            try {
                this.conn = DriverManager.getConnection(jdbcUrl, "root", "");
            }catch(Exception e) {
                e.printStackTrace();

            }

        }

        @Override
        public void add(User user) {
            String sql = "INSERT INTO USER(firstname,lastname, email, password) VALUE(????)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,user.getFirstname());
                preparedStatement.setString(2,user.getLastname());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

        @Override
        public void update(User updateUser) {
            // TODO : update user information in DB
            User user = new User();
            try {
                String sql = "UPDATE mooc SET firstname =?, lastname=?, email=? ,password=?  WHERE id=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setLong(5, updateUser.getId());
                preparedStatement.setString(1, updateUser.getFirstname());
                preparedStatement.setString(2, updateUser.getLastname());
                preparedStatement.setString(3, updateUser.getEmail());
                preparedStatement.setString(4, updateUser.getPassword());
                int rows = preparedStatement.executeUpdate();
                System.out.println(String.valueOf(rows)+" ligne modifié");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        @Override
        public User find(long id) {
            // TODO : get user data by its ID and map to User object
            User user = new User();
            try {
                String sql = "SELECT * from mooc WHERE id=?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    user.setId(resultSet.getLong("id"));
                    user.setFirstname(resultSet.getString("firstname"));
                    user.setLastname(resultSet.getString("lastname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return user;
        }


        @Override
        public User findByEmail(String email) {
            // TODO : get user data by its ID and map to User object
            String sql = "SELECT * FROM mooc WHERE email=?";
            User user = new User();
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,email);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    user.setId(resultSet.getLong("id"));
                    user.setFirstname(resultSet.getString("firstname"));
                    user.setLastname(resultSet.getString("lastname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                }

            }catch(SQLException e){
                e.printStackTrace();
            }
            return user;
        }

        @Override
        public long checkPassword(String email, String password) {
            // TODO : get user id that match, return -1 if none
            String sql = "Select * from user WHERE email=? and password=?";
            int returnValue =-1;
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1,email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    returnValue = resultSet.getInt("id");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return returnValue;
        }

        @Override
        public void delete(long id) {
            // TODO : delete a user that match this ID
            String sql ="DELETE FROM user WHERE id=?";
            try {
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setLong(1,id);
                int row = preparedStatement.executeUpdate();
                System.out.println("row effected "+String.valueOf(row));

            }catch (SQLException e){
                e.printStackTrace();
            }


        }

        @Override
        public long exists(String email) {
            // TODO : check if user with that mail exists
            throw new RuntimeException("not yet implemented");
        }



    }
