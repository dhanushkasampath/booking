INSERT INTO `user_type` (`id`,`created_date`,`is_deleted`,`last_modified_date`,`environment_type_id`,`param_key`,`param_value`,
                                     `scaling_config_param_type`) VALUES
                                                                      (1,current_timestamp(),_binary '\0',current_timestamp(),1,'MIN','2','NO_OF_REPLICAS'),
                                                                      (2,current_timestamp(),_binary '\0',current_timestamp(),1,'MAX','10','NO_OF_REPLICAS'),