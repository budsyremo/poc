DROP TABLE IF EXISTS providers;
 
CREATE TABLE providers (
  id INT AUTO_INCREMENT  ,
  provider_name VARCHAR(250) NOT NULL PRIMARY KEY,
  secret_key VARCHAR(250) NOT NULL,
  account_id VARCHAR(250)  not NULL,
  redirect_url VARCHAR(500)  not NULL
);
 
INSERT INTO providers (provider_name, secret_key, account_id,redirect_url) VALUES
  ('skills', 'talentcentral', 'talentcentral','http://localhost:9090/skills/mock');